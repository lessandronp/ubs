package br.com.lessandro.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.UbsDto;
import br.com.lessandro.model.AdaptationSenior;
import br.com.lessandro.model.Address;
import br.com.lessandro.model.City;
import br.com.lessandro.model.Geocode;
import br.com.lessandro.model.MedicalEquipment;
import br.com.lessandro.model.Medicine;
import br.com.lessandro.model.Score;
import br.com.lessandro.model.StructureSize;
import br.com.lessandro.model.Ubs;
import br.com.lessandro.repository.IUbsRepository;
import br.com.lessandro.repository.impl.UbsRepositoryBatch;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IAddressService;
import br.com.lessandro.service.IGeocodeService;
import br.com.lessandro.service.IScoreService;
import br.com.lessandro.service.IUbsService;
import br.com.lessandro.validator.GeocodeValidator;
import br.com.lessandro.validator.PageValidator;

@Service
public class UbsService implements IUbsService {

	Logger logger = LoggerFactory.getLogger(UbsService.class);

	@Autowired
	private IUbsRepository ubsRepository;
	
	@Autowired
	private UbsRepositoryBatch ubsRepositorySave;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private IScoreService scoreService;

	@Autowired
	private IGeocodeService geocodeService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<UbsDto> getAllUbs(int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		Page<Ubs> ubs = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "latitude", "longitude");
		ubs = ubsRepository.findAll(pageable);
		List<UbsDto> ubsDto = Arrays.asList(modelMapper.map(ubs.getContent(), UbsDto[].class));
		return new PageDto<>(ubsDto, ubs.getNumber(), ubs.getSize(), ubs.getTotalElements(), ubs.getTotalPages(),
				ubs.isLast());
	}
	
	@Override
	public PageDto<UbsDto> findTopUbsByGeocode(String query, int page, int size) throws ValidationException {
		PageValidator.validatePageSize(page, size);
		GeocodeValidator.validateGeocode(query);
		Page<Ubs> ubs = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size);
		String[] latitudeLongitude = query.split(",");
		ubs = ubsRepository.findTopUbsByGeocode(new BigDecimal(latitudeLongitude[0]), new BigDecimal(latitudeLongitude[1]), pageable);
		List<UbsDto> ubsDto = Arrays.asList(modelMapper.map(ubs.getContent(), UbsDto[].class));
		return new PageDto<>(ubsDto, ubs.getNumber(), ubs.getSize(), ubs.getTotalElements(), ubs.getTotalPages(),
				ubs.isLast());
	} 

	@Override
	public List<Ubs> readUbsFromCSV(InputStream inputStream) {
		List<Ubs> ubsList = new ArrayList<>();
		int countLine = 0;
		String line;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			while ((line = br.readLine()) != null) {
				if (countLine > 0) {
					String[] attributes = line.split(",");
					Ubs ubs = createUbs(attributes);
					ubsList.add(ubs);
				}
				countLine++;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return ubsList;
	}

	private Ubs createUbs(String[] metadata) {
		Integer cityCode = metadata[2] != null ? Integer.valueOf(metadata[2]) : null;
		City city = new City(null, cityCode, metadata[7]);
		Address address = new Address(null, metadata[5], metadata[6], city);
		StructureSize structureSize = new StructureSize(null, metadata[9]);
		AdaptationSenior adaptationSenior = new AdaptationSenior(null, metadata[10]);
		MedicalEquipment medicalEquipment = new MedicalEquipment(null, metadata[11]);
		Medicine medicine = new Medicine(null, metadata[12]);
		Score score = new Score(null, structureSize, adaptationSenior, medicalEquipment, medicine);
		Integer codCnes = metadata[3] != null ? Integer.valueOf(metadata[3]) : null;
		BigDecimal latitude = metadata[0] != null ? new BigDecimal(metadata[0]) : null;
		BigDecimal longitude = metadata[1] != null ? new BigDecimal(metadata[1]) : null;
		Geocode geocode = new Geocode(null, latitude, longitude);
		return new Ubs(null, metadata[4], codCnes, metadata[8], address, score, geocode);
	}

	@Override
	@Transactional
	public void saveUbsList(List<Ubs> ubsList) {
		int totalUbs = ubsList.size();
		for (Ubs ubs : ubsList) {
			try {
				ubs.setGeocode(geocodeService.prepareGeocode(ubs.getGeocode()));
				ubs.setAddress(addressService.prepareAddress(ubs.getAddress()));
				ubs.setScore(scoreService.prepareScore(ubs.getScore()));
				ubsRepositorySave.saveUbs(ubs);
				totalUbs--;
				System.out.println("Ubs restantes ".concat(String.valueOf(totalUbs)).concat(" de um total de ")
						.concat(String.valueOf(ubsList.size())));
			} catch (ValidationException e) {
				logger.error(e.getMessage());
			}
		}
	}

}

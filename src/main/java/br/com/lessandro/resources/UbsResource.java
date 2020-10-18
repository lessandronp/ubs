package br.com.lessandro.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lessandro.dto.PageDto;
import br.com.lessandro.dto.UbsDto;
import br.com.lessandro.resources.exception.ValidationException;
import br.com.lessandro.service.IUbsService;

@RestController
@RequestMapping("/api/v1")
public class UbsResource {

	@Autowired
	private IUbsService ubsService;
	
	@GetMapping(path = "/allUbs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUbs(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "per_page", required = false, defaultValue = "10") Integer per_page) {
		try {
			PageDto<UbsDto> ubs = ubsService.getAllUbs(page, per_page);
			return new ResponseEntity<>(ubs, HttpStatus.OK);
		} catch (ValidationException e) {
			return ResponseEntity.status(e.getStatus()).body(e.getMessage());
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(path = "/find_ubs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUbs(
			@RequestParam(value = "query", required = true) String query,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "per_page", required = false, defaultValue = "10") Integer per_page) {
		try {
			PageDto<UbsDto> ubs = ubsService.findTopUbsByGeocode(query, page, per_page);
			return new ResponseEntity<>(ubs, HttpStatus.OK);
		} catch (ValidationException e) {
			return ResponseEntity.status(e.getStatus()).body(e.getMessage());
		}
	}
}

package br.com.lessandro.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = { "name", "username" }, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_UBS")
public class User extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "S_USER_GENERATOR")
	@SequenceGenerator(name = "S_USER_GENERATOR", sequenceName = "SEQ_USER", initialValue = 1)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false), 
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false))
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles == null ? null : new ArrayList<>(roles);
	}

	public void setRoles(List<Role> roles) {
		if (roles == null) {
			this.roles = null;
		} else {
			this.roles = Collections.unmodifiableList(roles);
		}
	}

}

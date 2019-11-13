package br.com.divulplace.usuario.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.divulplace.usuario.entity.Usuario;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = -4568129352426493672L;

	private final Long id;

	private final String name;

	private final String username;

	private final String email;

	@JsonIgnore
	private final String password;

	private final boolean accountNonExpired;

	private final boolean accountNonLocked;

	private final boolean credentialsNonExpired;

	private final boolean enabled;

	private final Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(final Long id, final String name, final String username, final String email, final String password,
			final boolean accountNonExpired, final boolean accountNonLocked, final boolean credentialsNonExpired, final boolean enabled,
			final Collection<? extends GrantedAuthority> authorities) {

		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public static UserPrinciple build(final Usuario user) {

		final List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNome().name()))
				.collect(Collectors.toList());

		return new UserPrinciple(user.getId(), user.getNome(), user.getLogin(), user.getEmail(), user.getSenha(), true, true, true, true,
				authorities);
	}

	public static UserPrinciple build(final Long id, final String nome, final String username, final String email, final List<String> lista) {

		if (null == id || null == nome || null == email || null == lista) {

			throw new IllegalArgumentException();
		}

		return new UserPrinciple(id, nome, username, email, null, true, true, true, true,
				lista.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

	public Long getId() {

		return id;
	}

	public String getName() {

		return name;
	}

	@Override
	public String getUsername() {

		return username;
	}

	public String getEmail() {

		return email;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {

		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {

		return enabled;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserPrinciple other = (UserPrinciple) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}

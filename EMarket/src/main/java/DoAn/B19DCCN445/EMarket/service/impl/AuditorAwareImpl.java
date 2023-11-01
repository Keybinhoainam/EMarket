package DoAn.B19DCCN445.EMarket.service.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import DoAn.B19DCCN445.EMarket.model.User;

public class AuditorAwareImpl implements AuditorAware<User>{

	@Override
	public Optional<User> getCurrentAuditor() {
		return null;
	}

}

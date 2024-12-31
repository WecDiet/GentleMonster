package com.gentlemonster.Service.Token;

import com.gentlemonster.Repository.IUserRepository;
import com.gentlemonster.Utils.LocalizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenGeneratorService implements ITokenGeneratorService {
//    @Value("${time.expire.password}")
//    private long expireTimeInSeconds;
//    @Autowired
//    private IUserRepository iuserRepository;
//    @Autowired
//    private LocalizationUtil localizationUtil;

    @Override
    public String generateResetToken(String email) {

        return null;
    }

    @Override
    public String generateRandomString() {
        return null;
    }

}

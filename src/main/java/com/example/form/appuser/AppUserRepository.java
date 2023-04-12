package com.example.form.appuser;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
//user and then iam going to say find and then buy and then email
public interface AppUserRepository {

    Optional<AppUser> findByEmail(String email);
}

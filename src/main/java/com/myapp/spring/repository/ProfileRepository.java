package com.myapp.spring.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.myapp.spring.model.Profile;



@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
 Optional<Profile> findByEmail(String email);
}

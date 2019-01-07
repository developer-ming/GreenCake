package com.greencake.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greencake.domain.Wxuserinfo;

public interface WxUserRepository extends JpaRepository<Wxuserinfo, Integer> {
	@Query(value= "select distinct wu.wx_openid from Wxuserinfo wu", nativeQuery = true)
	public List<String> getMerchantWxOpenIds();
}

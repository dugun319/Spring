package com.oracle.obootDbConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.obootDbConnect.repository.MemberRepository;
import com.oracle.obootDbConnect.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {
	
	private DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    @Bean
    public MemberRepository memberRepository() {
		// return new JdbcMemberRepository(dataSource); -> Oracle DB
		// return new MemoryMemberRepository();         -> Memory DB
		return new MemoryMemberRepository();
	}
}

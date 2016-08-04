package com.allure.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 8/2/2016.
 */
public interface BaseRepository<T extends Model> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}

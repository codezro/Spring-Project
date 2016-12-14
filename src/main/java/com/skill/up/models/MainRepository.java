package com.skill.up.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jpmc on 11/16/2016.
 */
public interface MainRepository extends JpaRepository<MainForm, String> {
    List<MainForm> findAllById(Integer id);

    MainForm findOneByEmail(String username);
}
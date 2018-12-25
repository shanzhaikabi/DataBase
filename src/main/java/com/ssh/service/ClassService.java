package com.ssh.service;

import com.ssh.entity.Clazz;

import java.util.List;

public interface ClassService {
    public List<Clazz> ShowClassByName(String name);
}

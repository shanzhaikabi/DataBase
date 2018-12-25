package com.ssh.respository;

import com.ssh.entity.Clazz;

import java.util.List;

public interface ClazzRepository extends DomainRepository<Clazz,String> {
    List<Clazz> findByName(String name);
}

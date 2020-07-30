package org.george.pm.mapper;

public interface CurrentWorkSequenceMapper {

    Integer getCurrentMaxWorkId();

    Integer incWorkId(String incId);
}

package me.jackdavison.techtest.cmd;

import java.util.Collection;

public interface OutputBuilder<T> {
    public String buildOutput(Collection<T> elements);
}


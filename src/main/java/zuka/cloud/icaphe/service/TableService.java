package zuka.cloud.icaphe.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.Tables;

import java.util.Optional;

@Service
public interface TableService {

    Tables tables = new Tables();

    public Page findAllTable(Integer page, Integer size);

    public Optional<Tables> findById(Long id);

    public void save(Tables tables);

    public void remove(Tables tables);
}

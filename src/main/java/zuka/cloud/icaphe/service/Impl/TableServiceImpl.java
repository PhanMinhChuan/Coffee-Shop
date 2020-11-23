package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.Tables;
import zuka.cloud.icaphe.repository.TableRepository;
import zuka.cloud.icaphe.service.TableService;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableRepository tableRepository;

    public List<Tables> findAllUser() {
        return (List<Tables>) tableRepository.findAll();
    }

    @Override
    public Page findAllTable(Integer page, Integer size) {
        return tableRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Tables> findById(Long id) {
        return tableRepository.findById(id);
    }

    public void save(Tables tables) {
        tableRepository.save(tables);
    }

    public void remove(Tables tables) {
        tableRepository.delete(tables);
    }
}

package zuka.cloud.icaphe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zuka.cloud.icaphe.entity.Tables;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {

}

package com.bd.tpfinal.repositories;


        import com.bd.tpfinal.model.Supplier;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {


}

package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.entity.Invoice;
import yurii.sokolovskyi.mca.repository.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public Invoice findOneById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice with id " + id + " not exists"));
    }

}

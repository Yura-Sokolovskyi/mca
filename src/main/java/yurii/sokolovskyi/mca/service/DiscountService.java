package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.response.DiscountResponse;
import yurii.sokolovskyi.mca.entity.Discount;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.DiscountRepository;
import yurii.sokolovskyi.mca.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private UserRepository userRepository;


    public DiscountResponse getUserDiscountResponse(Long id){

        return new DiscountResponse(getUserDiscount(id));
    }

    public Double getUserDiscount(Long id){
        LocalDate date = LocalDate.now();
        User user = userRepository.getOne(id);
        LocalDate birthDay = user.getBirthDate();
        Discount discount = user.getDiscount();
        Double discountVal;
        if(discount == null){
            discountVal = 0.0;
        }else {
            discountVal = discount.getValue();
        }

        if(birthDay != null){
        if (date.getMonthValue() == birthDay.getMonthValue() && date.getDayOfMonth() == birthDay.getDayOfMonth()){
            discountVal += discountRepository.getDiscountByType("BIRTHDAY").getValue();
        }}
        System.out.println(discount);

        return discountVal;
    }




}

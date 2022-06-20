package com.example.testspringdata.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificateDTO {

    long id;
    String name;
    String description;
    String price;
    String duration;
    String createDate;
    String lastUpdateDate;
    TagDTO[] tags;

}

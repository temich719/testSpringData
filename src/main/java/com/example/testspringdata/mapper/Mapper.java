package com.example.testspringdata.mapper;

import com.example.testspringdata.dtos.GiftCertificateDTO;
import com.example.testspringdata.dtos.TagDTO;
import com.example.testspringdata.model.GiftCertificate;
import com.example.testspringdata.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public List<GiftCertificateDTO> mapToListDTO(List<GiftCertificate> giftCertificates){
        List<GiftCertificateDTO> giftCertificateDTOs = new ArrayList<>();
        for (GiftCertificate giftCertificate:giftCertificates) {
            giftCertificateDTOs.add(mapToDTO(giftCertificate));
        }
        return giftCertificateDTOs;
    }

    public GiftCertificateDTO mapToDTO(GiftCertificate giftCertificate){
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        giftCertificateDTO.setCreateDate(giftCertificate.getCreateDate());
        giftCertificateDTO.setDescription(giftCertificate.getDescription());
        giftCertificateDTO.setDuration(giftCertificate.getDuration());
        giftCertificateDTO.setId(giftCertificate.getId());
        giftCertificateDTO.setName(giftCertificate.getName());
        giftCertificateDTO.setLastUpdateDate(giftCertificate.getLastUpdateDate());
        giftCertificateDTO.setPrice(giftCertificate.getPrice());

        TagDTO[] tagDTOs = new TagDTO[giftCertificate.getTags().size()];

        int i = 0;
        for (Tag tag:giftCertificate.getTags()) {
            tagDTOs[i] = mapToTagDTO(tag);
            i++;
        }
        giftCertificateDTO.setTags(tagDTOs);
        return giftCertificateDTO;
    }

    public TagDTO mapToTagDTO(Tag tag){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }

}

package com.dev_cbj.community.comm_code.service;

import com.dev_cbj.community.comm_code.entity.CommCodeEntity;
import com.dev_cbj.community.comm_code.repository.CommCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommCodeService {
    private final CommCodeRepository commCodeRepository;

    public CommCodeEntity createCommCode(CommCodeEntity commCode) {
        return commCodeRepository.save(commCode);
    }

    public CommCodeEntity updateCommCode(CommCodeEntity commCode) {
        return commCodeRepository.save(commCode);
    }

    public void deleteCommCode(int id) {
        commCodeRepository.deleteById(id);
    }

    public List<CommCodeEntity> getAllCommCodes() {
        return commCodeRepository.findAll();
    }

    public CommCodeEntity getCommCodeById(int id) {
        return commCodeRepository.findById(id).orElse(null);
    }

    public CommCodeEntity changeUseStatus(int id, String useYn) {
        return commCodeRepository.findById(id).map(commCode -> {
            commCode.setUseYn(useYn);
            return commCodeRepository.save(commCode);
        }).orElse(null);
    }
}

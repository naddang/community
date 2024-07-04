package com.dev_cbj.community.comm_code.controller;

import com.dev_cbj.community.comm_code.entity.CommCodeEntity;
import com.dev_cbj.community.comm_code.service.CommCodeService;
import com.dev_cbj.community.common.response.ResponseDTO;
import com.dev_cbj.community.common.response.ResponseDataDTO;
import com.dev_cbj.community.common.response.ResponseListDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comm-codes")
@AllArgsConstructor
public class CommCodeController {
    private final CommCodeService commCodeService;

    // 리스트 조회
    @GetMapping
    public ResponseEntity<ResponseListDTO<CommCodeEntity>> getAllCommCodes() {
        HttpStatus status;
        ResponseListDTO<CommCodeEntity> result;

        //권한 검사

        status = HttpStatus.OK;
        result = new ResponseListDTO<>(status);
        List<CommCodeEntity> commCodes = commCodeService.getAllCommCodes();
        result.setTotalCount(commCodes.size());
        result.setList(commCodes);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDataDTO<CommCodeEntity>> getCommCode(@PathVariable int id) {
        HttpStatus status;
        ResponseDataDTO<CommCodeEntity> result;

        CommCodeEntity commCode = commCodeService.getCommCodeById(id);

        status = commCode == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        result = new ResponseDataDTO<>(status);
        result.setData(commCode);

        return ResponseEntity.ok(result);
    }

    // 등록
    @PostMapping
    public ResponseEntity<ResponseDTO> createCommCode(@RequestBody CommCodeEntity commCode) {
        HttpStatus status;
        ResponseDTO result;

        CommCodeEntity createdCommCode = commCodeService.createCommCode(commCode);

        status = createdCommCode == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        result = new ResponseDTO(status);

        return ResponseEntity.ok(result);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateCommCode(@PathVariable int id, @RequestBody CommCodeEntity commCode) {
        HttpStatus status;
        ResponseDTO result;

        commCode.setSeq(id);
        CommCodeEntity updatedCommCode = commCodeService.updateCommCode(commCode);

        status = updatedCommCode == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        result = new ResponseDTO(status);

        return ResponseEntity.ok(result);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCommCode(@PathVariable int id) {
        HttpStatus status;
        ResponseDTO result;

        commCodeService.deleteCommCode(id);

        status = HttpStatus.OK;
        result = new ResponseDTO(status);

        return ResponseEntity.ok(result);
    }

    //사용 상태 변경
    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO> updateCommCodeStatus(@PathVariable int id, @RequestBody CommCodeEntity commCode) {
        HttpStatus status;
        ResponseDTO result;

        commCode.setSeq(id);
        CommCodeEntity updatedCommCode = commCodeService.changeUseStatus(id, commCode.getUseYn());

        status = updatedCommCode == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        result = new ResponseDTO(status);

        return ResponseEntity.ok(result);
    }
}

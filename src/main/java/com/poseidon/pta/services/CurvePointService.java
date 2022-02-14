package com.poseidon.pta.services;

import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

@Service
public class CurvePointService extends BaseService<CurvePoint>{
    public CurvePointService(CurvePointRepository curveRepository) {
        super(curveRepository);
    }
}

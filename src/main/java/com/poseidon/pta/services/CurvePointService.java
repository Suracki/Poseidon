package com.poseidon.pta.services;

import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

/**
 * CurvePointService performs operations for the CurveController endpoints
 *
 * Extends BaseService<CurvePoint>, passes a CurvePointRepository object to this super.
 * All functionality is contained within BaseService.
 */
@Service
public class CurvePointService extends BaseService<CurvePoint>{
    public CurvePointService(CurvePointRepository curveRepository) {
        super(curveRepository);
    }
}

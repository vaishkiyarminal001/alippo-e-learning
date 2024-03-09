package com.app.service;

import com.app.exception.NotFoundException;
import com.app.models.Certificate;

public interface CertificateService {


    Certificate generateCertificateForStudent(Long instructorId, Long studentId, Long courseId) throws NotFoundException;

    Certificate getCertificateById(Long userId, Long certificateId) throws NotFoundException;

}

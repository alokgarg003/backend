//package com.cdac.model;
//
//import java.sql.Date;
//
//import com.cdac.dto.RequestCertificateDto;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
//@Entity
//public class Certificate {
//
//	public Certificate() {
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String name;
//	private String issuer;
//	private Date date;
//	private String certificateName;
//	private Long userId;
//	private boolean isAuthorized;
//
//	@Lob
//	private byte[] pdfContent;
//
//	public Certificate(RequestCertificateDto dto, Long userId) {
//		this.name = dto.getName();
//		this.issuer = dto.getIssuer();
//		long millis = System.currentTimeMillis();
//		this.date = new Date(millis);
//		this.certificateName = dto.getCertificateName();
//		this.userId = userId;
//	}
//
//}
package com.cdac.model;

import java.sql.Date;

import com.cdac.dto.RequestCertificateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "certificate")
public class Certificate {

    public Certificate() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "issuer", nullable = false, length = 100)
    private String issuer;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "certificate_name", nullable = false, length = 100)
    private String certificateName;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "is_authorized", nullable = false, columnDefinition = "boolean default false")
    private boolean isAuthorized;

    @Lob
    @Column(name = "pdf_content", columnDefinition = "LONGBLOB")
    private byte[] pdfContent;

    public Certificate(RequestCertificateDto dto, Long userId) {
        this.name = dto.getName();
        this.issuer = dto.getIssuer();
        long millis = System.currentTimeMillis();
        this.date = new Date(millis);
        this.certificateName = dto.getCertificateName();
        this.userId = userId;
        this.isAuthorized = false; // Setting default value
    }
}
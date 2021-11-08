package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Entity
@Data
public class Employee {
    @Id
    /** 従業員コード */
    @Column (name="username")
    private String username;

    /** 従業員名 */
    @Column (name="empname")
    private String empname;

    @Column (name="profile")
    private String profile;

    /** ログインパスワード */
    @Column (name="password")
    private String password;

    /** パスワード有効期限 */
    @Column (name="passwordlimit")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate passwordlimit;

    /** 役割 */
    @Column (name="userrole")
    private int userrole;
}
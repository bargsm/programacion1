package com.myjavablog.repository;
import com.myjavablog.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    //List<Contact> findByName(String name);


/*
    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by name desc" , nativeQuery=true)
    List <Contact> findByName(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by name asc" , nativeQuery=true)
    List <Contact> findAllByOrderByNameAsc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by name desc" , nativeQuery=true)
    List <Contact> findAllByOrderByNameDesc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by surname asc" , nativeQuery=true)
    List <Contact> findAllByOrderBySurnameDesc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by surname  desc" , nativeQuery=true)
    List <Contact> findAllByOrderBySurnameAsc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by type asc" , nativeQuery=true)
    List <Contact> findAllByOrderByTypeDesc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by type  desc" , nativeQuery=true)
    List <Contact> findAllByOrderByTypeAsc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by description asc" , nativeQuery=true)
    List <Contact> findAllByOrderByDescriptionDesc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by description  desc" , nativeQuery=true)
    List <Contact> findAllByOrderByDescriptionAsc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by information asc" , nativeQuery=true)
    List <Contact> findAllByOrderByInformationDesc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name%) and state =:state order by information  desc" , nativeQuery=true)
    List <Contact> findAllByOrderByInformationAsc(@Param("name") String name, @Param("state") String state);

    @Query(value="SELECT * FROM CONTACT where state =:state order by name desc" , nativeQuery=true)
    List <Contact> findAllByStateOrderByNameDesc(@Param("state") String state);

*/


    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name% ) and state like :state% and userid =:userid order by name desc" , nativeQuery=true)
    List <Contact> findByName(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name% ) and state like :state%  and userid =:userid  order by name asc" , nativeQuery=true)
    List <Contact> findAllByOrderByNameAsc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name%) and state like :state%  and userid =:userid  order by name desc" , nativeQuery=true)
    List <Contact> findAllByOrderByNameDesc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name% ) and state like :state%  and userid =:userid  order by surname asc" , nativeQuery=true)
    List <Contact> findAllByOrderBySurnameDesc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name%) and state like :state% and userid =:userid  order by surname  desc" , nativeQuery=true)
    List <Contact> findAllByOrderBySurnameAsc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name%) and state like :state%  and userid =:userid  order by type asc" , nativeQuery=true)
    List <Contact> findAllByOrderByTypeDesc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name%) and state like :state%  and userid =:userid  order by type  desc" , nativeQuery=true)
    List <Contact> findAllByOrderByTypeAsc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name%) and state like :state%  and userid =:userid  order by description asc" , nativeQuery=true)
    List <Contact> findAllByOrderByDescriptionDesc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name%) and state like :state% and userid =:userid  order by description  desc" , nativeQuery=true)
    List <Contact> findAllByOrderByDescriptionAsc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name% ) and state like :state%  and userid =:userid  order by information asc" , nativeQuery=true)
    List <Contact> findAllByOrderByInformationDesc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where (upper(name) like %:name% or upper(surname) like %:name% or upper(name_and_surname) like %:name% ) and state like :state%  and userid =:userid  order by information  desc" , nativeQuery=true)
    List <Contact> findAllByOrderByInformationAsc(@Param("name") String name, @Param("state") String state, @Param("userid") int userid);

    @Query(value="SELECT * FROM CONTACT where state like :state% and userid =:userid  order by name desc" , nativeQuery=true)
    List <Contact> findAllByStateOrderByNameDesc(@Param("state") String state, @Param("userid") int userid);






}

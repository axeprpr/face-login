package xyz.funfan.facelogin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.funfan.facelogin.dao.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

}

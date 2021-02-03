package com.sport.rach;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sport.rach.klub.model.Adres;
import com.sport.rach.klub.model.Czlonek;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.klub.model.Organ;
import com.sport.rach.klub.model.OrganTyp;
import com.sport.rach.klub.model.Rejestracja;
import com.sport.rach.rachunki.model.Koszty;
import com.sport.rach.rachunki.model.KosztyZd;
import com.sport.rach.rachunki.model.Podmiot;
import com.sport.rach.rachunki.model.Przychody;
import com.sport.rach.rachunki.model.PrzychodyZd;
import com.sport.rach.user.model.ERole;
import com.sport.rach.user.model.Role;
import com.sport.rach.user.model.User;

@Repository
@Transactional
public class TestDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void createUsersWithKlubs() {
		User user = new User("Andrzej", "rota@o2.pl", passwordEncoder.encode("jaroszowiec"));
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Role> query = cb.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);
		query.where(cb.equal(root.get("name"), ERole.ROLE_USER));
		Role userRole = em.createQuery(query).getSingleResult();
		user.getRoles().add(userRole);
		em.persist(user);
		Organ organ = new Organ("Zarząd", OrganTyp.ORGAN_ZARZAD);
		Czlonek c1 = new Czlonek("Andrzej", "Rotarski", "65032014014", "prezes");
		Czlonek c2 = new Czlonek("Andrzej", "Nowak", "65032014014", "członek");
		organ.getSklad().add(c1);
		organ.getSklad().add(c2);
		c1.setOrgan(organ);
		c2.setOrgan(organ);
	//	em.persist(organ);
		
		Organ organNadzor = new Organ("Rada Nadzorcza", OrganTyp.ORGAN_NADZORU);
		Czlonek c1n = new Czlonek("Andrzej", "Rotarski", "65032014014", "prezes");
		Czlonek c2n = new Czlonek("Andrzej", "Nowak", "65032014014", "członek");
		organNadzor.getSklad().add(c1n);
		organNadzor.getSklad().add(c2n);
		c1n.setOrgan(organNadzor);
		c2n.setOrgan(organNadzor);
	//	em.persist(organNadzor);
		
		Adres klubAdres = new Adres("Jaroszowiec", "Kolejowa","25","32312");
		Klub klub = new Klub("LKS Unia Jaroszowiec", klubAdres, 12345, 56987);
		em.persist(klub);
		user.setKlub(klub);
		em.merge(user);
		Adres adresRejestr =  new Adres("Kraków", "Mogilska","25","32312");
		Rejestracja rejestracja = new Rejestracja("Sąd Kraków", adresRejestr, 256987);
	//	em.persist(rejestracja);
		klub.setZarzad(organ);
		
		klub.setRejestracja(rejestracja);
		klub.setNadzor(organNadzor);
		
		
		Organ organ2 = new Organ("Zarząd", OrganTyp.ORGAN_ZARZAD);
		Czlonek c12 = new Czlonek("Andrzej", "Rotarski", "65032014014", "prezes");
		Czlonek c22 = new Czlonek("Andrzej", "Nowak", "65032014014", "członek");
		organ2.getSklad().add(c12);
		organ2.getSklad().add(c22);
		c12.setOrgan(organ2);
		c22.setOrgan(organ2);
	//	em.persist(organ);
		
		Organ organNadzor2 = new Organ("Rada Nadzorcza", OrganTyp.ORGAN_NADZORU);
		Czlonek c1n2 = new Czlonek("Andrzej", "Rotarski", "65032014014", "prezes");
		Czlonek c2n2 = new Czlonek("Andrzej", "Nowak", "65032014014", "członek");
		organNadzor2.getSklad().add(c1n2);
		organNadzor2.getSklad().add(c2n2);
		c1n2.setOrgan(organNadzor2);
		c2n2.setOrgan(organNadzor2);
	//	em.persist(organNadzor);
		User user2 = new User("rotarski", "rotar@o2.pl", passwordEncoder.encode("20marzec"));
		user2.getRoles().add(userRole);
		em.persist(user2);
		
		Adres klubAdres2 = new Adres("Jaroszowiec", "Kolejowa","25","32312");
		Klub klub2 = new Klub("LKS Unia Jaroszowiec", klubAdres2, 12345, 56987);
		em.persist(klub2);
		user2.setKlub(klub2);
		em.merge(user2);
		
		Adres adresRejestr2 =  new Adres("Kraków", "Mogilska","25","32312");
		Rejestracja rejestracja2 = new Rejestracja("Sąd Kraków", adresRejestr2, 256987);
	//	em.persist(rejestracja);
		klub2.setZarzad(organ2);
		
		klub2.setRejestracja(rejestracja2);
		klub2.setNadzor(organNadzor2);
	}

	public void addRachunki() {
		Klub klub = this.em.find(Klub.class, 1L);
		
		
		for(int i = 0; i < 30; i++) {
			Adres a1 = new Adres(
					"Olkusz"+i,
					"kolejowa"+i,
					"6", "32310");
			Podmiot p = new Podmiot("Ph Leviatan"+ i, 1256, a1);
			p.setKlub(klub);
			KosztyZd k1 = new KosztyZd( p, new Date(), "f1/02", "zakup butów", 48.28, klub, Koszty.UZYSKANIA_P);
			em.persist(k1);
		}
		for(int i = 0; i < 15; i++) {
			Adres a1 = new Adres(
					"Jaroszowiec"+i,
					"kolejowa"+i,
					"6", "32310");
			Podmiot p = new Podmiot("Gmina"+ i, 1256, a1);
			p.setKlub(klub);
			PrzychodyZd k1 = new PrzychodyZd( p, new Date(), "f1/02", "dotacja", 25000.00, klub, Przychody.NIEODPLATNE_PP);
			em.persist(k1);
		}
		
		
	}
	
	public void addRachunki2() {
		Klub klub = this.em.find(Klub.class, 2L);
		
		
		for(int i = 0; i < 2000; i++) {
			Adres a1 = new Adres(
					"Olkusz"+i,
					"kolejowa"+i,
					"6", "32310");
			Podmiot p = new Podmiot("Ph Leviatan"+ i, 1256, a1);
			p.setKlub(klub);
			KosztyZd k1 = new KosztyZd( p, new Date(), "f1/02", "zakup butów", 48.28, klub, Koszty.UZYSKANIA_P);
			em.persist(k1);
		}
		for(int i = 0; i < 1500; i++) {
			Adres a1 = new Adres(
					"Jaroszowiec"+i,
					"kolejowa"+i,
					"6", "32310");
			Podmiot p = new Podmiot("Gmina"+ i, 1256, a1);
			p.setKlub(klub);
			PrzychodyZd k1 = new PrzychodyZd( p, new Date(), "f1/02", "dotacja", 25000.00, klub, Przychody.NIEODPLATNE_PP);
			em.persist(k1);
		}
		
		
	}
	
	public void updateKlub() {
		Klub klub = em.find(Klub.class, 1L);
		em.lock(klub, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		klub.setNazwa(klub.getNazwa()+"1");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateKlub2() throws RuntimeException{
		Klub klub = em.find(Klub.class, 1L);
		em.lock(klub, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		klub.setNazwa(klub.getNazwa()+"1");

	}
	
	
}

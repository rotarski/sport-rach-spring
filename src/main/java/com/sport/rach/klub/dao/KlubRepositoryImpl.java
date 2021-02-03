package com.sport.rach.klub.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sport.rach.klub.model.Klub;
import com.sport.rach.klub.model.Organ;
import com.sport.rach.user.dao.UserRepository;
import com.sport.rach.user.model.User;

@Component
public class KlubRepositoryImpl {
	@PersistenceContext
	EntityManager em;
	@Autowired
	KlubRepository klubRepository;

	@Autowired
	UserRepository userRepository;

	// @Transactional
	public Klub getKlubFetch(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Klub> query = cb.createQuery(Klub.class);
		Root<Klub> root = query.from(Klub.class);
		root.fetch("nadzor", JoinType.LEFT);
		root.fetch("rejestracja", JoinType.LEFT);
		Fetch<Klub, Organ> organ = root.fetch("zarzad", JoinType.LEFT);
		organ.fetch("sklad", JoinType.LEFT);
		query.select(root);
		query.where(cb.equal(root.get("id"), id));
		Klub o = em.createQuery(query).getSingleResult();
		if (o.getNadzor() != null) {
			o.getNadzor().getSklad().size();
		}
		return o;
	}

	@Transactional
	public void removeKlubfirstRemoveSklady(Long userId) {

		User user = em.find(User.class, userId);
		Long klubId = user.getKlub().getId();
		user.setKlub(null);

		Klub klub = em.find(Klub.class, klubId);
//		List<Czlonek> forDeleteCzlonek = new ArrayList<>();
//		if(klub.getNadzor() != null) {
//			forDeleteCzlonek.addAll(klub.getNadzor().getSklad());
//		}
//		if(klub.getZarzad() != null) {
//			forDeleteCzlonek.addAll(klub.getZarzad().getSklad());
//		}
//
//		if(forDeleteCzlonek.size()>0) {			
//			Query q1 = em.createQuery("DELETE FROM Czlonek c WHERE c IN(:sklad)" );		
//			q1.setParameter("sklad", forDeleteCzlonek).executeUpdate();
//			
//		}
		em.remove(klub);
		em.close();
	}

	@Transactional
	public Optional<Klub> saveKlub(Klub klub, Long userId) {

			User user = this.userRepository.findById(userId).orElseThrow();
			klub = this.klubRepository.save(klub);
			user.setKlub(klub);
			this.userRepository.save(user);
			return Optional.of(klub);

	}

	public void deleteKlubByUserId(Long userId) {

		User user = this.userRepository.findById(userId).orElseThrow();
		Long klubId = user.getKlub().getId();
		user.setKlub(null);
		this.userRepository.save(user);
		this.klubRepository.deleteById(klubId);
	};



	@Transactional
	public Optional<Klub> updateKlub(Klub klub) {

		klub = this.klubRepository.save(klub);
		return Optional.of(klub);
	}

}

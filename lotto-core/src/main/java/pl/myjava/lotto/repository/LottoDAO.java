package pl.myjava.lotto.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.myjava.lotto.repository.entitis.LottoGameEntity;

@Stateless
public class LottoDAO {
	@PersistenceContext(name="primary")
	private EntityManager entityManager;
	
	public Long save(LottoGameEntity gameEntity) {
		Long result = null;
		
		if (gameEntity != null) {
			entityManager.persist(gameEntity);
		}
		
		return result;
	}
	
	public List<LottoGameEntity> getAll() {
		return entityManager.createQuery("select from " + LottoGameEntity.class.getName(), LottoGameEntity.class).getResultList();
	}
	
	public LottoGameEntity getById(Long id) {
		return entityManager.find(LottoGameEntity.class, id);
	}
}

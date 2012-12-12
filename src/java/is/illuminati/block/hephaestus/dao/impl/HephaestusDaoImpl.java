package is.illuminati.block.hephaestus.dao.impl;

import is.illuminati.block.hephaestus.dao.HephaestusDao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.impl.GenericDaoImpl;

@Repository("hephaestusDao")
@Transactional(readOnly = true)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class HephaestusDaoImpl extends GenericDaoImpl implements
		HephaestusDao {

}
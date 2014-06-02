package com.t1m0.spring.SpringRestOauth.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import com.t1m0.spring.SpringRestOauth.entities.Client;
import com.t1m0.spring.SpringRestOauth.entities.User;
import com.t1m0.spring.SpringRestOauth.services.interfaces.LIClient;

@Transactional
@Component(ClientService.BEAN_NAME)
public class ClientService implements LIClient {
	
	private final Logger LOG = LoggerFactory.getLogger(ClientService.class);

	public final static String BEAN_NAME = "customClientDetails";

	/** The sql all. */
	private final String SQL_ALL = "select c from Client c";

	/** The sql all. */
	private final String SQL_BY_CLIENTID = "select c from Client c where c.clientId = :id";

	/** The manager. */
	@PersistenceContext
	private EntityManager manager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client create(final Client a) {
		manager.persist(a);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client read(final long id) {
		return manager.find(Client.class, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client update(final Client a) {
		manager.merge(a);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Client a) {
		manager.remove(a);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Client> all() {
		Query q = manager.createQuery(SQL_ALL, Client.class);
		return q.getResultList();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Query q = manager.createQuery(SQL_BY_CLIENTID, Client.class);
		q.setParameter("id",clientId);
		Client c = null;
		try{
			c = (Client)q.getSingleResult();
		}catch(Exception e){
			String err = "Client id '"+clientId+"' not found!";
			LOG.error(err);
			throw new UsernameNotFoundException(err);
		}
		return c;
	}

}

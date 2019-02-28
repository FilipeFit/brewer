package com.insightsoftware.brewer.repository.helper.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.insightsoftware.brewer.model.Cliente;
import com.insightsoftware.brewer.repository.filter.ClienteFilter;
import com.insightsoftware.brewer.repository.paginacao.PaginacaoUtil;

public class ClienteRepositoryImpl implements ClienteRepositoryQueries {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  PaginacaoUtil paginacaoUtil;

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable) {
    Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cliente.class);

    paginacaoUtil.preparar(criteria, pageable);
    adicionaFiltro(filter, criteria);
    criteria.createAlias("endereco.cidade", "c", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("c.estado", "e", JoinType.LEFT_OUTER_JOIN);

    return new PageImpl<>(criteria.list(), pageable, total(filter));
  }

  private void adicionaFiltro(ClienteFilter filter, Criteria criteria) {

    if (filter != null) {
      if (!StringUtils.isEmpty(filter.getNome())) {
        criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
      }

      if (!StringUtils.isEmpty(filter.getCpfOuCnpj())) {
        criteria.add(Restrictions.eq("cpfOuCnpj", filter.getCpfOuCnpj()));
      }
    }

  }

  private Long total(ClienteFilter filter) {
    Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cliente.class);
    adicionaFiltro(filter, criteria);
    criteria.setProjection(Projections.rowCount());
    return (Long) criteria.uniqueResult();
  }

}

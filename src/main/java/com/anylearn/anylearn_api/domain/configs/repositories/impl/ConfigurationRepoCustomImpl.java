// package com.anylearn.anylearn_api.domain.configs.repositories.impl;

// import java.util.List;

// import com.anylearn.anylearn_api.domain.configs.entity.Configuration;
// import com.anylearn.anylearn_api.domain.configs.repositories.ConfigurationRepoCustom;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;
// import jakarta.persistence.TypedQuery;

// public class ConfigurationRepoCustomImpl implements ConfigurationRepoCustom {

//     @PersistenceContext
//     private EntityManager entityManager;

//     @Override
//     public List<Configuration> findByKeys(List<String> keys) {
//         String sql = "SELECT c FROM configurations c WHERE key IN (:keys)";
//         TypedQuery<Configuration> query = entityManager.createQuery(sql, Configuration.class);
//         query.setParameter("keys", keys);
//         return query.getResultList();
//     }
    
// }

 SELECT pcx.codigo
       , pcx.nome 
       , sum(xx.saldo_inicial) AS saldo_inicial 
       , sum(xx.entrada)       AS entrada
       , sum(xx.saida)         AS saida 
       , sum(xx.periodo)       AS periodo
       , sum(xx.saldo_final)   AS saldo_final 
    FROM
       (
        SELECT x.conta_id
             , coalesce(
               (
                SELECT sum
                       ( 
                        CASE WHEN tipo = 0 THEN
                                  l.valor
                             WHEN tipo = 1 THEN
                                  l.valor * -1 
                             ELSE
                                  0.00
                        END
                       ) 
                  FROM lancamentoContabil l
                  LEFT JOIN MovimentacaoContabil m on m.id = movimentacao_id 
                 WHERE m.data < '2012-07-04'
                   AND conta_id  = x.conta_id
               ),0)  AS saldo_inicial 
             , sum(x.entrada) AS entrada
             , sum(x.saida)   AS saida
             , sum(x.periodo) AS periodo
             , coalesce(
               (
                 SELECT sum
                        ( 
                         CASE WHEN tipo = 0 THEN
                                   l.valor
                              WHEN tipo = 1 THEN
                                   l.valor * -1 
                              ELSE
                                   0.00
                         END
                        ) 
                   FROM lancamentoContabil l
                   LEFT JOIN MovimentacaoContabil m on m.id = movimentacao_id 
                   WHERE m.data <=  '2015-07-14'
                    AND conta_id = x.conta_id
               ),0)  AS saldo_final
          FROM
             ( 
               SELECT lan.conta_id
                    , CASE WHEN lan.tipo = 0 THEN
                                lan.valor
                           ELSE
                                0.00 
                      END AS entrada 
                    , CASE WHEN lan.tipo = 1 THEN
                                lan.valor
                           ELSE
                                0.00 
                      END AS saida 
                    , CASE WHEN lan.tipo = 0 THEN
                                   lan.valor
                              WHEN lan.tipo = 1 THEN
                                   lan.valor * -1 
                              ELSE
                                   0.00
                         END as periodo
                 FROM lancamentocontabil AS lan
                 LEFT JOIN MovimentacaoContabil m on m.id = lan.movimentacao_id 
                WHERE m.data >= '2012-07-04'
                  AND m.data <= '2016-07-14'
             ) AS x
      GROUP BY x.conta_id
       ) AS xx
    JOIN planoconta pcx 
      ON xx.conta_id = pcx.id
GROUP BY pcx.id 
       , pcx.nome 
ORDER BY pcx.id ASC
       ; 
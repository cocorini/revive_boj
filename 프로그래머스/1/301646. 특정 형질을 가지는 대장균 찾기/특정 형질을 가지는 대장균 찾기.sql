-- 코드를 작성해주세요

SELECT COUNT(IF(GENOTYPE&2=0 AND (GENOTYPE&1=1 OR GENOTYPE&4=4), 1, NULL)) AS 'COUNT'
FROM ECOLI_DATA
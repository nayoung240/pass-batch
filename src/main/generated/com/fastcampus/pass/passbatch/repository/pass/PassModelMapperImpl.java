package com.fastcampus.pass.passbatch.repository.pass;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T23:08:56+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class PassModelMapperImpl implements PassModelMapper {

    @Override
    public PassEntity toPassEntity(BulkPassEntity bulkPassEntity, String userId) {
        if ( bulkPassEntity == null && userId == null ) {
            return null;
        }

        PassEntity passEntity = new PassEntity();

        if ( bulkPassEntity != null ) {
            passEntity.setStatus( status( bulkPassEntity.getStatus() ) );
            passEntity.setRemainingCount( bulkPassEntity.getCount() );
            passEntity.setPackageSeq( bulkPassEntity.getPackageSeq() );
            passEntity.setStartedAt( bulkPassEntity.getStartedAt() );
            passEntity.setEndedAt( bulkPassEntity.getEndedAt() );
        }
        passEntity.setUserId( userId );

        return passEntity;
    }
}

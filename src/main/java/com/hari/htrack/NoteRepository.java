package com.hari.htrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    /*   at least one tag
     @Query("SELECT DISTINCT n FROM Note n JOIN n.tags t " +
                "WHERE (:tagNames IS NULL OR LOWER(t.name) IN :tagNames)")
    */
    //all tags should be there
//    @Query("SELECT n FROM Note n JOIN n.tags t " +
//            "WHERE LOWER(t.name) IN :tagNames " +
//            "GROUP BY n.id " +
//            "HAVING COUNT(DISTINCT t.name) = (SELECT COUNT(DISTINCT t2.name) FROM Tag t2 WHERE LOWER(t2.name) IN :tagNames)")
   //when empty tags all notes should come
    @Query("SELECT n FROM Note n LEFT JOIN n.tags t " +
            "GROUP BY n.id " +
            "HAVING :tagCount = 0 OR COUNT(DISTINCT CASE WHEN LOWER(t.name) IN :tagNames THEN t.name END) = :tagCount")
    List<Note> searchNotes(@Param("tagNames") List<String> tagNames, @Param("tagCount") long tagCount);
}


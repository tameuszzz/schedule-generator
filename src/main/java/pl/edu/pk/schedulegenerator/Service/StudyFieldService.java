package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.StudyFieldDAO;
import pl.edu.pk.schedulegenerator.Entity.StudyField;
import pl.edu.pk.schedulegenerator.Entity.StudyFieldUpdate;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudyFieldService {

    @Autowired
    private StudyFieldDAO dao;

    public Collection<StudyField> getStudyFields() {
        return dao.getStudyFields();
    }

    public StudyField postStudyField(StudyField studyField) {
        return dao.postStudyField(studyField);
    }

    public Optional<StudyField> getStudyFieldsById(String id) {
        return dao.getStudyFieldsById(id);
    }

    public Optional<StudyField> deleteStudyFieldById(String id) {
        return dao.deleteStudyFieldById(id);
    }

    public Optional<StudyField> updateStudyFieldById(String id, StudyFieldUpdate studyFieldUpdate) {
        return dao.updateStudyFieldById(id, studyFieldUpdate);
    }

}

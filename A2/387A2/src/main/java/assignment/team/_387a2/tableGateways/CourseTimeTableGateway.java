package assignment.team._387a2.tableGateways;

import assignment.team._387a2.domainObjects.CourseTime;
import assignment.team._387a2.mappers.CourseTimeMapper;

import java.util.List;

public class CourseTimeTableGateway {
    private CourseTimeMapper courseTimeMapper;

    public CourseTimeTableGateway() {
        courseTimeMapper = new CourseTimeMapper();
    }

    public List<CourseTime> findByCourseId(int pCourseId) {
        return courseTimeMapper.findByCourseId(pCourseId);
    }

    public CourseTime findByCourseTimeId(int pCourseTimeId) {
        return courseTimeMapper.find(pCourseTimeId);
    }

    public List<CourseTime> getAll() {
        return courseTimeMapper.getAll();
    }

    public void updateCourseTime(CourseTime pCourseTime) {
        courseTimeMapper.update(pCourseTime);
    }

    public void insertCourseTime(CourseTime pCourseTime) {
        courseTimeMapper.insert(pCourseTime);
    }

    public void deleteCourseTime(CourseTime pCourseTime) {
        courseTimeMapper.delete(pCourseTime);
    }

    public void deleteCourseTimeByCourseTimeId(int pCourseTimeID) {
        courseTimeMapper.deleteCourseTimeByCourseTimeId(pCourseTimeID);
    }

    public void deleteCourseTimeByCourseId(int pCourseID) {
        courseTimeMapper.deleteCourseTimeByCourseId(pCourseID);
    }
}

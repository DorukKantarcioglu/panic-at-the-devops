import { createContext } from "react";
import CourseService from "../service/CourseService";

//Here, we set the initial fontSize as 16.
const courseContext = createContext(CourseService.getAllCourses().then());
export default courseContext;

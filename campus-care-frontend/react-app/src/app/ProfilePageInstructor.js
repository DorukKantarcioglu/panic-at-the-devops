import { BrowserRouter as Router, Link, Route, Switch } from "react-router-dom";
import InfoBox from "./components/profileComponents/InfoBox";
import {Container, Col, Row} from 'react-bootstrap';
import StudentList from "./components/profileComponents/StudentList";
import CoursePage from "./CoursePage";

class ProfilePageInstructor extends React.Component {
    constructor() {
        super();
        this.state = {
            name: "Elif",
            surname: "Cenesiz",
            id: 21902461,
            hesCode: "XXX",
        };
    }

    render() {
        return (
            <Container>
                <Row>
                    <InfoBox/>
                </Row>
                <Row>
                    <CoursePage/>
                </Row>
                <Row>
                    Appointments (To-do)
                </Row>
            </Container>
        );
    }
}
export default ProfilePageInstructor;
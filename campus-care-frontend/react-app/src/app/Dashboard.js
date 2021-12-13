import React from 'react';
import CovidInfoBox from './components/CovidInfoBox';
import FormInput from './components/FormInput';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

    
function Dashboard(props) {
 
  // handle click event of logout button
  const handleLogout = () => {    
    props.history.push('/login');
  }
 
  return (
    <React.Fragment>
      <div className="d-flex flex-row justify-content-around mb-5 ">
      
        <CovidInfoBox
           
          background-color = "coral"
          key="1"
          name={" Live Count"}
          value={2000}
          icon="fas fa-users"
        />

        <FormInput></FormInput>
        <CovidInfoBox
          key="2"
          name={"New Cases In This Week"}
          value={1200}
          icon="fas fa-clipboard-check"
        />
        <CovidInfoBox
          key="3"
          name={"Total Count"}
          value={2300}
          icon="fas fa-university "
        />
      </div>

    </React.Fragment>
  );
}
export default Dashboard;

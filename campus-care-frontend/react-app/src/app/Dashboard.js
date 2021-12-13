import React from 'react';
import CovidStatisticsBox from './components/CovidStatisticsBox';
 
function Dashboard(props) {
 
  // handle click event of logout button
  const handleLogout = () => {    
    props.history.push('/login');
  }
 
  return (
    <React.Fragment>
      <div className="d-flex flex-row justify-content-around mb-5 ">    

        <CovidStatisticsBox
          key="1"
          name={" Live Count"}
          value={2000}
          icon="fas fa-users"
        />
        <CovidStatisticsBox
          key="2"
          name={"New Cases In This Week"}
          value={1200}
          icon="fas fa-clipboard-check"
        />
        <CovidStatisticsBox
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

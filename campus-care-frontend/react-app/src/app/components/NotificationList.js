const NotificationList = (props) => {
  const notifications = [
    {
      header: "covid risk",
      date: "3 ",
      content: "There is a covid student in your className",
    },
    {
      header: "covid risk",
      date: "4 ",
      content: "Risky student in your cs className",
    },
  ];

  return (
    <div>
      {notifications &&
        notifications.map((notifications) => {
          return (
            <a href="#" className="list-group-item list-group-item-action">
              <div className="d-flex w-100 justify-content-between">
                <h5 className="mb-1">{notifications.header}</h5>
                <small>{notifications.date} days ago</small>
              </div>
              <p className="mb-1">{notifications.content}</p>
            </a>
          );
        })}
    </div>
  );
};
export default NotificationList;

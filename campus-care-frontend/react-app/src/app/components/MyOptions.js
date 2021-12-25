import React from 'react';
import classes from './MyButton.module.css';

const MyOptions = ({children, ...props}) => {
    return (
        <option {...props} className={classes.myBtn}>
            {children}
        </option>
    );
};

export default MyOptions;

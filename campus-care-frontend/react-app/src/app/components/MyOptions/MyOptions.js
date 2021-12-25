import React from 'react';

const MyOptions = ({options}) => {
    return (
        <div>
            {options.map(option =>
                <option>{option}</option>
            )}
        </div>
    );
};

export default MyOptions;

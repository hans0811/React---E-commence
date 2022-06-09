import React, { Fragment } from 'react';
import { ReactComponet as CrwnLogo } from '../../assets/crown.svg';
import { Outlet, Link } from 'react-router-dom';

const Navigation = () => {
  return (
    <Fragment>
      <div className="navigation">
        <Link calssName="logo-conatiner" to="/">
          <CrwnLogo calssName="logo" />
        </Link>

        <div className="nav-links-container">
          <Link calssName="nav-link" to="/shop">
            SHOP
          </Link>
        </div>
      </div>
      <Outlet />
    </Fragment>
  );
};

export default Navigation;

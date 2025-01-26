import React from "react";
import { Link } from "react-router-dom";
import { Container, Typography, Button, Box } from "@mui/material";

const Dashboard = () => {
    return (
        <Container maxWidth="md">
            <Box my={4} textAlign="center">
                <Typography variant="h4" gutterBottom>
                    Welcome to the Dashboard
                </Typography>
                <Typography variant="body1" gutterBottom>
                    Here you can navigate to different parts of the app.
                </Typography>
                <Box mt={2}>
                    <Link to="/expenses" style={{ textDecoration: "none" }}>
                        <Button variant="contained" color="primary">
                            Go to Expenses
                        </Button>
                    </Link>
                </Box>
            </Box>
        </Container>
    );
};

export default Dashboard;

function getGroupByTeamItems() {
    return [{
        xtype: 'combobox',
        fieldLabel: 'Select Team',
        store: getTeamStore(),
        queryMode: 'local',
        displayField: 'id',
        valueField: 'id'
    }, {
        xtype: 'tabpanel',
        activeTab: 0,
        tabPosition: 'top',
        items: [{
            title: 'Team Profile',
            items: getTeamProfileItems()
        },{
            title: 'View Matches',
            items: getTeamMatchItems()
        }]
    }];
}

function getTeamProfileItems() {
    return [{
        defaultType: 'container',
        layout: {
            type: 'vbox',
            align: 'left'
        },
        items: [{
            defaultType: 'container',
            layout: {
                type: 'hbox',
                align: 'left'
            },
            items: [{
                xtype: 'gridpanel',
                title: 'Event Averages',
                id: 'eventAverageGrid',
                //store: getTeamMatchStore(),
                columns: [
                    { header: 'Event',  dataIndex: 'id', flex: 1},
                    { header: 'Auton. Points', dataIndex: 'autonomous' },
                    { header: 'Teleop Points', dataIndex: 'teleop'},
                    { header: 'Climb Points', dataIndex: 'climb'},
                    { header: 'Total Points', dataIndex: 'total_points'}
                ],
                width: 500,
                margin: 10
            }, {
                defaultType: 'container',
                layout: {
                    type: 'vbox'
                },
                items: [{
                    xtype: 'panel',
                    border: false,
                    html: 'Team profile area',
                    width: 350,
                    height: 300,
                    margin: 10
                }, {
                    xtype: 'filefield',
                    buttonOnly: true,
                    margin: '0 0 0 100'
                }]
            }]
        }, {
            defaultType: 'container',
            layout: {
                type: 'hbox'
            },
            items: [{
                xtype: 'chart',
                defaultType: 'container',
                animate: true,
                width: 500,
                height: 300,
                margin: 25,
                store: getTeamEventsStore(),
                legend: {
                    position: 'bottom'
                },
                axes: [{
                    type: 'Numeric',
                    position: 'left',
                    title: 'Points Scored',
                    grid: true,
                    minimum: 0
                },{
                    type: 'Category',
                    position: 'bottom',
                    fields: ['id'],
                    title: 'Match #'
                }],
                series: [{
                    type: 'line',
                    axis: 'left',
                    xField: 'id',
                    yField: 'event1'
                }, {
                    type: 'line',
                    axis: 'left',
                    xField: 'id',
                    yField: 'event2'
                }]
            }, {
                xtype: 'chart',
                defaultType: 'container',
                width: 350,
                height: 250,
                margin: '25 0 0 0',
                animate: true,
                theme:'Category2',
                store: getRadarChartStore(),
                axes: [{
                    type: 'Radial',
                    position: 'radial',
                    label: {
                        display: true
                    }
                }],
                series: [{
                    type: 'radar',
                    xField: 'category',
                    yField: 'data',
                    showMarkers: true,
                    showInLegend: true,
                    markerConfig: {
                        radius: 5,
                        size: 5
                    },
                    style: {
                        'stroke-width': 2,
                        fill: 'none'
                    }
                }]
            }]
        }]
    }];
}

function getTeamMatchItems() {
    return [{
        defaultType: 'container',
        layout: {
            type: 'vbox',
            align: 'left'
        },
        items: [{
            defaultType: 'container',
            xtype: 'gridpanel',
            //store: ,
            columns: [
                { header: 'Event',  dataIndex: 'event'},
                { header: 'Match #', dataIndex: 'match_id' },
                { header: 'Auton Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
            ],
            margin: 10,
        }, {
            xtype: 'button',
            text: 'View Match Record',
            handler: function() {
                alert('Open match record with all details filled in.');
            },
            margin: '0 0 10 500'
        }]
    }];
}
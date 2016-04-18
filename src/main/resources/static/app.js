/**
 * This file provided by Facebook is for non-commercial testing and evaluation
 * purposes only. Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


var Race = React.createClass({
    render: function() {
        return (
            <tr>
                <td>{this.props.name}</td>
                <td>{this.props.date}</td>
                <td>{this.props.city}</td>
                <td>{this.props.raceType}</td>
            </tr>
        );
    }
});

var RaceBox = React.createClass({
    loadRacesFromServer: function() {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                this.setState({data: data});
            }.bind(this),
            error: function(xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function() {
        return {data: []};
    },
    componentDidMount: function() {
        this.loadRacesFromServer();
        setInterval(this.loadRacesFromServer, this.props.pollInterval);
    },
    render: function() {
        return (
            <div className="raceBox">
                <h1>Races</h1>
                <RaceList data={this.state.data} />
            </div>
        );
    }
});


var RaceList = React.createClass({
    render: function() {
        var races = this.props.data.map(function(race) {
            return (
                <Race name={race.name} date={race.date} city={race.city} raceType={race.raceType} />
            );
        });
        return (
            <table>
                <tr>
                    <th>Name</th>
                    <th>Date</th>
                    <th>City</th>
                    <th>Type</th>
                </tr>
                {races}
            </table>
        );
    }
});


ReactDOM.render(
    <RaceBox url="/races" pollInterval={60000} />,
    document.getElementById('content')
);
